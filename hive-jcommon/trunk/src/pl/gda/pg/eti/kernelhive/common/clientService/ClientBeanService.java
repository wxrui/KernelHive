
package pl.gda.pg.eti.kernelhive.common.clientService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "ClientBeanService", targetNamespace = "http://engine.kernelhive.eti.pg.gda.pl/", wsdlLocation = "http://localhost:8080/ClientBeanService/ClientBean?wsdl")
public class ClientBeanService
    extends Service
{

    private final static URL CLIENTBEANSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(pl.gda.pg.eti.kernelhive.common.clientService.ClientBeanService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = pl.gda.pg.eti.kernelhive.common.clientService.ClientBeanService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/ClientBeanService/ClientBean?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/ClientBeanService/ClientBean?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        CLIENTBEANSERVICE_WSDL_LOCATION = url;
    }

    public ClientBeanService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ClientBeanService() {
        super(CLIENTBEANSERVICE_WSDL_LOCATION, new QName("http://engine.kernelhive.eti.pg.gda.pl/", "ClientBeanService"));
    }

    /**
     * 
     * @return
     *     returns ClientBean
     */
    @WebEndpoint(name = "ClientBeanPort")
    public ClientBean getClientBeanPort() {
        return super.getPort(new QName("http://engine.kernelhive.eti.pg.gda.pl/", "ClientBeanPort"), ClientBean.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ClientBean
     */
    @WebEndpoint(name = "ClientBeanPort")
    public ClientBean getClientBeanPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://engine.kernelhive.eti.pg.gda.pl/", "ClientBeanPort"), ClientBean.class, features);
    }

}
