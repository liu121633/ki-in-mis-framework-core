package org.kingqueen.kiinmis.web.action.Repair;


import org.kingqueen.kiinmis.model.biz.Repair.RepairBiz;
import org.kingqueen.kiinmis.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
//@RequestMapping("Repair")
public class Repair {

    @Autowired
    private HttpSession session;

    @Autowired
    private RepairBiz repairBiz;

    @ResponseBody
    //@RequestMapping("hourrecord")
    public Map<String, Object> repairHourrecord() {
        User user = (User) session.getAttribute("user");
        
            return repairBiz.repairHourrecord(user);

    }

}
