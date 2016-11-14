package code;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import ws.niceview.*;


@XmlRootElement
public class HotelInformationList {
    
    private List<HotelType> list;
    
    public List<HotelType> getList() {
        return list;
    }
    
    public void setList (List<HotelType> list){    
        this.list = list;
    } 
}