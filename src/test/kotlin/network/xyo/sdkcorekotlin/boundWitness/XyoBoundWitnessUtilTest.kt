package network.xyo.sdkcorekotlin.boundWitness

import kotlinx.coroutines.runBlocking
import network.xyo.sdkcorekotlin.XyoTestBase
import network.xyo.sdkcorekotlin.schemas.XyoSchemas
import network.xyo.sdkobjectmodelkotlin.buffer.XyoBuff
import network.xyo.sdkobjectmodelkotlin.objects.XyoIterableObject
import org.junit.Assert
import org.junit.Test

class XyoBoundWitnessUtilTest : XyoTestBase() {

    @Test
    fun testRemoveTypeFromWitnesses () {
        val boundWitnessBytes = "600201A22015CB2019C8000C41170F9302323929FD3FD8A72851F73866A0BFC6D488040E9D921689E01B9E25E4393B0984576763DD9C5DA95E609A80B4CC12064758C1AEEE28AE264015BF474F000D8200AEB335766EC511499DDE566579B4ED1562079AA543388B2EDED68ED68363AE9DAE25E7E29B9A5607E676A5F50CC6EB5CBCEBDEE30FB3F1CB9DA0074D4D3CA29B8BFD42AEEE44CA7C26134F4401FF67332C549AD72B36FBF9211D07B0B825C137D6A0DD13EE35FE446B55D22E66CE751216DC4BB823A3A62C3D0208CAC0DF68AB2017D1201ACA00094421009A0FF234B98891EE3FF99365A3CA6AB804173F1A8619934134A68F59FBDCA92E200C04A196D4A39C987C984E18B79D3EE81667DD92E962E6C630DB5D7BDCDB1988000A81713AB83E5D8B4EF6D2EAB4D70B61AADCA01E733CB0B3D072DE307CDBCD09F46D528A7159EB73DEBB018871E30D182F5BBB426689E758A7BFD4C51D0AD116CA621BF1C39DA49A837D525905D22BAB7C1874F6C7E6B4D56139A15C3BE1D1DC8E061C241C060A24B588217E37D6206AFE5D71F4698D42E25C4FCE996EECCF7690B900130200".hexStringToByteArray()
        val createdBoundWitness = XyoBoundWitness.getInstance(boundWitnessBytes)
        val removedBoundWitness = XyoBoundWitnessUtil.removeTypeFromUnsignedPayload(XyoSchemas.RSSI.id, createdBoundWitness)

        for (witness in removedBoundWitness[XyoSchemas.FETTER.id]) {
            if (witness is XyoIterableObject) {
                for (item in witness.iterator) {
                    if (item.schema.id == XyoSchemas.RSSI.id) {
                        throw Exception("Found an rssi in FETTER!")
                    }
                }
            }
        }
    }

    @Test
    fun testGetPartyNumFromRealPublicKey () {
        runBlocking {
            val boundWitnessBytes = "6002012B201547201944000C4192BAF8FBA41F6B5CA997DF7634F1F33176E0DDA8F7B485C6CD2EBC3BA06D4EEC8BB98284DB33761BA8A7668D1A5C140384968A0BE3436067F10A0D6B7F5AAFFF201547201944000C41ED1512DA596726D8E19A592BBA5573D31174C424FDFD7A0D14B3088BD22F0EB520F99E19D78DBBD613B79277FEB2BD0911C4C379E69B8688CC744B5B5ACF928F20174A201A470009442100CAC1C5F12BCCEA80C176FCCEEFEC616E86A9F208F43E45D49E8F32F76278B9F8202ABFC11D935F56D5CFECFDC66D4CA37D67C69AE6CD3C1DB41794C3C7FF41FE90201749201A4600094320656984EF23EAD4304E4A1AB3321F64BF9629FFE0E3A4097B181C2295892578D2205B90DAD8607D3BE600209771E2A19EC9EA3BB7BEE9D44A99395E85577FBCDBB7".hexStringToByteArray()
            val publicKeyBytes = "000C41ED1512DA596726D8E19A592BBA5573D31174C424FDFD7A0D14B3088BD22F0EB520F99E19D78DBBD613B79277FEB2BD0911C4C379E69B8688CC744B5B5ACF928F".hexStringToByteArray()
            val publicKey = XyoBuff.wrap(publicKeyBytes)

            val createdBoundWitness = XyoBoundWitness.getInstance(boundWitnessBytes)
            val partyNumOfPublicKey = XyoBoundWitnessUtil.getPartyNumberFromPublicKey(createdBoundWitness, publicKey)

            Assert.assertEquals(1, partyNumOfPublicKey)
        }
    }

    @Test
    fun testGetPartyNumFromFakePublicKey () {
        runBlocking {
            val boundWitnessBytes = "6002012B201547201944000C4192BAF8FBA41F6B5CA997DF7634F1F33176E0DDA8F7B485C6CD2EBC3BA06D4EEC8BB98284DB33761BA8A7668D1A5C140384968A0BE3436067F10A0D6B7F5AAFFF201547201944000C41ED1512DA596726D8E19A592BBA5573D31174C424FDFD7A0D14B3088BD22F0EB520F99E19D78DBBD613B79277FEB2BD0911C4C379E69B8688CC744B5B5ACF928F20174A201A470009442100CAC1C5F12BCCEA80C176FCCEEFEC616E86A9F208F43E45D49E8F32F76278B9F8202ABFC11D935F56D5CFECFDC66D4CA37D67C69AE6CD3C1DB41794C3C7FF41FE90201749201A4600094320656984EF23EAD4304E4A1AB3321F64BF9629FFE0E3A4097B181C2295892578D2205B90DAD8607D3BE600209771E2A19EC9EA3BB7BEE9D44A99395E85577FBCDBB7".hexStringToByteArray()
            val publicKeyBytes = "000C41ED1512DA596721D8E19A592BBA5573D31174C424FDFD7A0D14B3088BD22F0EB520F99E19D78DBBD613B79277FEB2BD0911C4C379E69B8688CC744B5B5ACF928F".hexStringToByteArray()
            val publicKey = XyoBuff.wrap(publicKeyBytes)

            val createdBoundWitness = XyoBoundWitness.getInstance(boundWitnessBytes)
            val partyNumOfPublicKey = XyoBoundWitnessUtil.getPartyNumberFromPublicKey(createdBoundWitness, publicKey)

            Assert.assertEquals(null, partyNumOfPublicKey)
        }
    }
}