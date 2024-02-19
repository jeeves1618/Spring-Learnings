package net.myphenotype.DBwithJDBCtemplate.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "IT_PROJECTS")
public class ItProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal projectId;
    private String projectName;
    private Date startDate;
    private Date targetEndDate;
    private Date actualEndDate;
    private Date createdOn;
    private String createdBy;
    private Date modifiedOn;
    private String modifiedBy;

}
