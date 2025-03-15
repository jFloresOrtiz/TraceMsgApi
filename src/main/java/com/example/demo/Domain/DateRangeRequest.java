package com.example.demo.Domain;

import jakarta.annotation.Nonnull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateRangeRequest {
    @NonNull
    public Date from;

    @Nonnull
    public  Date to;
    
}
