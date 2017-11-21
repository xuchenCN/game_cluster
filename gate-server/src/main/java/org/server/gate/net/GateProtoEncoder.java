package org.server.gate.net;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * body-length -> messageId -> body
 *
 */
public class GateProtoEncoder extends MessageToByteEncoder<GateClientMessage> {

	@Override
	protected void encode(ChannelHandlerContext ctx, GateClientMessage msg, ByteBuf out) throws Exception {
		int bodyLen = msg.body.readableBytes();
		int headerLen = computeRawVarint32Size(bodyLen);
		int msgIdLen = computeRawVarint32Size(msg.messageId.getNumber());
		out.ensureWritable(headerLen + msgIdLen + bodyLen);
		writeRawVarint32(out, bodyLen);
		writeRawVarint32(out, msg.messageId.getNumber());
		out.writeBytes(msg.body, msg.body.readerIndex(), bodyLen);
	}

	/**
	 * Writes protobuf varint32 to (@link ByteBuf).
	 * 
	 * @param out
	 *          to be written to
	 * @param value
	 *          to be written
	 * @throws IOException
	 */
	static void writeRawVarint32(ByteBuf out, int value) throws IOException {
		while (true) {
			if ((value & ~0x7F) == 0) {
				out.writeByte(value);
				return;
			} else {
				out.writeByte((value & 0x7F) | 0x80);
				value >>>= 7;
			}
		}
	}

	/**
	 * Computes size of protobuf varint32 after encoding.
	 * 
	 * @param value
	 *          which is to be encoded.
	 * @return size of value encoded as protobuf varint32.
	 */
	static int computeRawVarint32Size(final int value) {
		if ((value & (0xffffffff << 7)) == 0) {
			return 1;
		}
		if ((value & (0xffffffff << 14)) == 0) {
			return 2;
		}
		if ((value & (0xffffffff << 21)) == 0) {
			return 3;
		}
		if ((value & (0xffffffff << 28)) == 0) {
			return 4;
		}
		return 5;
	}

}
