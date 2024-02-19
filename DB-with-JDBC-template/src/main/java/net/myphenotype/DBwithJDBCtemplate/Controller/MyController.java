package net.myphenotype.DBwithJDBCtemplate.Controller;

import net.myphenotype.DBwithJDBCtemplate.Config.LoadDataOnStartUp;
import net.myphenotype.DBwithJDBCtemplate.Entity.ItProjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/db")
public class MyController {

    @Autowired
    LoadDataOnStartUp loadDataOnStartUp;

    @GetMapping(path = "/projects")
    public List<ItProjects> getProjects(){
        return loadDataOnStartUp.getProjects();
    }
}
