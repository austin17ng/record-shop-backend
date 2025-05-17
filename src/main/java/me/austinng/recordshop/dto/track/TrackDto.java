package me.austinng.recordshop.dto.track;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto {
    private long id;
    private String title;
    private int duration;
    private int trackNumber;
}
