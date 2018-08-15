package it.gov.***REMOVED***.securitymanager.service.utilities


import it.gov.***REMOVED***.common.sso.common.CacheWrapper
import it.gov.***REMOVED***.common.utils.{Credentials, Profile}
import play.api.mvc.RequestHeader
import it.gov.***REMOVED***.common.sso.common.CredentialManager
import scala.util.Try

object Utils {

  def getCredentials( requestHeader: RequestHeader, cacheWrapper:CacheWrapper ):Try[Credentials] = {

    Try{
      CredentialManager.readCredentialFromRequest(requestHeader) match {
        case p:Profile => cacheWrapper.getPwd(p.username) match{
          case Some(pwd) => Credentials(p.username, pwd, p.groups)
          case None => throw new Exception("Can't find credentails in cache")
        }
        case c:Credentials => c
      }
    }

  }

}
