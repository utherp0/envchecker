package org.uth;

import javax.ws.rs.*;
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

  @Path("/search")
  @GET
  @Produces("text/html")
  public String performSearch(@QueryParam("terms") String terms )
  {
    Map<String,String> allEnvs = System.getenv();

    StringBuilder output = null;

    int results = 0;
    long start = System.currentTimeMillis();

    for( String key : allEnvs.keySet() )
    {
      // Match?
      if( key.toLowerCase().indexOf( terms.toLowerCase() ) != -1 )
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

        results++;
      }
    }

    long end = System.currentTimeMillis();

    return "Search for <b>" + terms + "</b> took <b>" + ( end - start ) + "ms</b><br/>" + ( output == null ? "Found 0 results." : "Found <b>" + results + "</b><br/>" + output.toString());
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
