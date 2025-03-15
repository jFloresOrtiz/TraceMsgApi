package com.example.demo.Domain;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "traceMsg")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraceMsg {
    @Id
    public ObjectId _id;
    public String sessionId;
    public String payload;
    public Date ts;
}
