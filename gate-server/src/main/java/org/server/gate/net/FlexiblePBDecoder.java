package org.server.gate.net;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;

import io.netty.buffer.ByteBuf;

public class FlexiblePBDecoder {
	
	private static final boolean HAS_PARSER;

  static {
      boolean hasParser = false;
      try {
          // MessageLite.getParserForType() is not available until protobuf 2.5.0.
          MessageLite.class.getDeclaredMethod("getParserForType");
          hasParser = true;
      } catch (Throwable t) {
          // Ignore
      }

      HAS_PARSER = hasParser;
  }
	
	public static Object decode(MessageLite prototype, ExtensionRegistryLite extensionRegistry , ByteBuf msg) throws Exception {
		final byte[] array;
		final int offset;
		final int length = msg.readableBytes();
		if (msg.hasArray()) {
			array = msg.array();
			offset = msg.arrayOffset() + msg.readerIndex();
		} else {
			array = new byte[length];
			msg.getBytes(msg.readerIndex(), array, 0, length);
			offset = 0;
		}

		if (extensionRegistry == null) {
			if (HAS_PARSER) {
				return prototype.getParserForType().parseFrom(array, offset, length);
			} else {
				return prototype.newBuilderForType().mergeFrom(array, offset, length).build();
			}
		} else {
			if (HAS_PARSER) {
				return prototype.getParserForType().parseFrom(array, offset, length, extensionRegistry);
			} else {
				return prototype.newBuilderForType().mergeFrom(array, offset, length, extensionRegistry).build();
			}
		}
	}
}
