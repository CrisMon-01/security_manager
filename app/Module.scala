import com.google.inject.{AbstractModule, Singleton}
import it.gov.***REMOVED***.common.sso.common.{CacheWrapper, LoginClient}
import it.gov.***REMOVED***.securitymanager.service.utilities.ConfigReader
import it.gov.***REMOVED***.sso.LoginClientLocal
import play.api.{Configuration, Environment, Logger}

import scala.sys.process._

@Singleton
class Module(***REMOVED***ironment: Environment, configuration: Configuration) extends AbstractModule{


  def configure(): Unit ={


    Logger.debug("executing module..")

    Logger.debug("--***REMOVED***--")
    Logger.debug("ls -l".!! )
    Logger.debug("-------")
    Logger.debug("ls -l conf".!! )
    Logger.debug("-------")
    //Logger.debug("ls -l conf/mnt".!! )
    //Logger.debug("-------")

    bind(classOf[LoginClient]).to(classOf[LoginClientLocal])//for the initialization of SecuredInvocationManager

    //val cookieExpiration = configuration.getLong("cookie.expiration")
    //val tokenExpiration = configuration.getLong("token.expiration")
    val cookieExpiration =  Option(ConfigReader.cookieExpiration.toLong)
    val tokenExpiration = Option(ConfigReader.tokenExpiration.toLong)

    val cacheWrapper = new CacheWrapper(cookieExpiration,tokenExpiration)
    cacheWrapper.putCredentials(ConfigReader.suspersetOpenDataUser,ConfigReader.suspersetOpenDataPwd)
    bind(classOf[CacheWrapper]).toInstance(cacheWrapper)

  }

}


