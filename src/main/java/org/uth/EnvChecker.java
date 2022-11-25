package org.uth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/envs")
public class EnvChecker 
{
  @Path("/getall")
  @GET
  @Produces("text/html")
  public String lookupAll() 
  {
    Map<String,String> allEnvs = System.getenv();

    StringBuilder output = null;

    for( String key : allEnvs.keySet() )
    {
      String value = System.getenv(key);

      if( output == null )
      {
        output = new StringBuilder( "<b>" + key + ":</b> " + value );
      }
      else
      {
        output.append( "<br/><b>" + key + ":</b> " + value );
      }
    }

    return ( output == null ? "No envs found" : output.toString() );
  }

  @Path("/getenv")
  @GET
  @Produces("text/html")
  public String lookupEnv(@QueryParam("target") String target )
  {
    String value = System.getenv( target );

    return ( value == null ? "<b>No Env Variable in Container - " + target + "</b>" : "<b>" + target + ":</b> " + value ); 
  } 
}
