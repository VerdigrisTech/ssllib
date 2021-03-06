package co.verdigris.ssl

import java.security.{KeyFactory, PrivateKey}

import co.verdigris.security.spec.PKCS1EncodedKeySpec
import org.apache.commons.codec.binary.Base64

/** Factory for instantiating [[PrivateKey]] from PKCS1 encoded string in PEM format. */
object PrivateKeyFactory {
  val BEGIN_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----"
  val END_PRIVATE_KEY = "-----END RSA PRIVATE KEY-----"

  /** Create a PrivateKey from PKCS1 encoded string in PEM format.
    *
    * @param pem string representing the RSA private key encoded with PKCS1
    * @return a new [[PrivateKey]] instance
    */
  def fromPEM(pem: String): PrivateKey = {
    val kf = KeyFactory.getInstance("RSA")
    val pemBytes = Base64.decodeBase64(pem.stripPrefix(BEGIN_PRIVATE_KEY).stripPrefix(END_PRIVATE_KEY))
    val keySpec = new PKCS1EncodedKeySpec(pemBytes)

    kf.generatePrivate(keySpec)
  }
}
