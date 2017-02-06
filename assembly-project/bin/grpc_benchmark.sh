#!/usr/bin/env bash

usage="Usage: grpc_benchmark.sh  (start|stop) <command>  "

# if no args specified, show usage
if [ $# -le 1 ]; then
  echo $usage
  exit 1
fi

# get arguments
startStop=$1
shift
command=$1
shift


if [ "$command" = "server" ]; then
  CLASS=org.protocol.ServerGateServices
elif [ "$command" = "client" ]; then
  CLASS=org.protocol.BenchmarkClientTest
else
  echo "command required 'server' or 'client'"
  exit 1
fi
 

if [ "$JAVA_HOME" = "" ]; then
  echo "Error: JAVA_HOME is not set."
  exit 1
fi

if [ "$TEST_HOME" = "" ]; then
  TEST_HOME=$(pwd)
fi

if [ "$TEST_PID_DIR" = "" ]; then
  TEST_PID_DIR=/tmp
fi

if [ "$TEST_LOG_DIR" = "" ]; then
  TEST_LOG_DIR=$TEST_HOME/logs
fi


# Set default scheduling priority
if [ "$EXEC_NICENESS" = "" ]; then
    export EXEC_NICENESS=0
fi


export TEST_LOGFILE=test-$command-$HOSTNAME.log
export TEST_ROOT_LOGGER=${TEST_ROOT_LOGGER:-INFO,DRFA}
log=$TEST_LOG_DIR/$TEST_LOGFILE
log_out=$TEST_LOG_DIR/test-$command-$HOSTNAME.out
pid=$TEST_PID_DIR/test-$command.pid
TEST_STOP_TIMEOUT=${TEST_STOP_TIMEOUT:-5}

CLASSPATH="${CLASSPATH}"
CLASSPATH="$TEST_HOME/lib/*:${CLASSPATH}:${TEST_HOME}"

JAVA=$JAVA_HOME/bin/java

case $startStop in

  (start)

    [ -w "$TEST_PID_DIR" ] || mkdir -p "$TEST_PID_DIR"
    [ -w "$TEST_LOG_DIR" ] || mkdir -p "$TEST_LOG_DIR"
    [ -w "$log_out" ] || touch "$log_out"

    if [ -f $pid ]; then
      if kill -0 `cat $pid` > /dev/null 2>&1; then
        echo $command running as process `cat $pid`.  Stop it first.
        exit 1
      fi
    fi
  

    

    echo starting $command, logging to $log
    cd "$TEST_HOME"
    nohup nice -n $EXEC_NICENESS "$JAVA" -classpath "$CLASSPATH"  $CLASS  "$@" > "$log" 2>&1 < /dev/null &
    echo $! > $pid
    sleep 1
    # capture the ulimit output
    echo "ulimit -a" >> $log_out
    ulimit -a >> $log_out 2>&1
    ;;

  (stop)

    if [ -f $pid ]; then
      TARGET_PID=`cat $pid`
      if kill -0 $TARGET_PID > /dev/null 2>&1; then
        echo stopping $command
        kill $TARGET_PID
        sleep $TEST_STOP_TIMEOUT
        if kill -0 $TARGET_PID > /dev/null 2>&1; then
          echo "$command did not stop gracefully after $TEST_STOP_TIMEOUT seconds: killing with kill -9"
          kill -9 $TARGET_PID
        fi
      else
        echo no $command to stop
      fi
      rm -f $pid
    else
      echo no $command to stop
    fi
    ;;

  (*)
    echo $usage
    exit 1
    ;;

esac

