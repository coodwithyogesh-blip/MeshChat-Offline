package com.meshchat.offline
import com.meshchat.offline.mesh.*
import org.junit.Assert.assertEquals
import org.junit.Test
class PacketCodecTest{@Test fun roundTrip(){val p=PacketEnvelope.create("a","d","text","hello");assertEquals(p,PacketCodec.decode(PacketCodec.encode(p)))}}
