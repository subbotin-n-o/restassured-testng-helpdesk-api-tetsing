package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Integer id;
    private String due_date;

    @JsonIgnore
    private String assigned_to;

    private String title;
    private String created;
    private String modified;
    private String submitter_email;
    private Integer status;
    private Boolean on_hold;
    private String description;
    private String resolution;
    private Integer priority;
    private String last_escalation;
    private String secret_key;
    private Integer queue;

    @JsonIgnore
    private Integer kbitem;

    @JsonIgnore
    private Integer merged_to;
}