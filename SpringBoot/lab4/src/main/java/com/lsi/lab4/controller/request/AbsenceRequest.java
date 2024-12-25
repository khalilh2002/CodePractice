package com.lsi.lab4.controller.request;

import java.util.Date;

public record AbsenceRequest(
  Long studentId,
  Boolean haveReason,
  String reason,
  Date date
) { }
