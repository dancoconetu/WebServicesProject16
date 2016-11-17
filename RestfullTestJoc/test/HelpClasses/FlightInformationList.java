package HelpClasses;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import ws.lameduck.*;


@XmlRootElement
public class FlightInformationList {
    
    private List<FlightInformation> list;
    
    public List<FlightInformation> getList() {
        return list;
    }
    
    public void setList (List<FlightInformation> list){    
        this.list = list;
    } 
}