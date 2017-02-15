## cshap
.\protoc\win\tools\ProtoGen.exe -output_directory=.\src\main\csharp  .\src\main\proto\message_identify.proto

##java
--proto_path=./src/main/proto/ --java_out=./src/main/gen-java/ ./src/main/proto/*