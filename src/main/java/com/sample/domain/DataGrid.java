package com.sample.domain;

import lombok.Data;

@Data
public class DataGrid {
  private String address;
  private String password;
  private Integer lockTimeToLiveSeconds;
}
