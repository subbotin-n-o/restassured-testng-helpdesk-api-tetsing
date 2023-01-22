package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return status.equals(ticket.status) &&
                priority.equals(ticket.priority) &&
                queue.equals(ticket.queue) &&
                (title.equals(ticket.title) || title.equals(ticket.getTitle())) &&
                (resolution.equals(ticket.resolution) || resolution.equals(ticket.getResolution()));

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, due_date, assigned_to, title,
                created, modified, submitter_email, status,
                on_hold, description, resolution, priority,
                last_escalation, secret_key, queue, kbitem, merged_to);
    }
}