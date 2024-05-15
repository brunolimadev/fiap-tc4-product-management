package br.com.fiap.productmanagement.domain.entities;

import br.com.fiap.productmanagement.domain.exception.EntityException;

import java.time.LocalDateTime;

public class DateTimeSchedulingEntity {

  private LocalDateTime dateTime;

  public void setSchedulingDateTime(LocalDateTime dateTime) {

    this.dateTime = dateTime;

  }

  public String getSchedulingDatetimeFormatted() throws EntityException {

    try {

      if (dateTime == null) {

        dateTime = LocalDateTime.now().plusSeconds(1);

        var second = dateTime.getSecond();
        var minute = dateTime.getMinute();
        var hour = dateTime.getHour();
        var day = dateTime.getDayOfMonth();
        var month = dateTime.getMonthValue();
        var dayOfWeek = dateTime.getDayOfWeek().getValue();

        return String.format("%d %d %d %d %d %d",
                second, minute, hour, day, month, dayOfWeek) ;

      }

      var second = dateTime.getSecond();
      var minute = dateTime.getMinute();
      var hour = dateTime.getHour();
      var day = dateTime.getDayOfMonth();
      var month = dateTime.getMonthValue();
      var dayOfWeek = dateTime.getDayOfWeek().getValue();

      return String.format("%d %d %d %d %d %d",
              second, minute, hour, day, month, dayOfWeek) ;

    } catch (Exception exception) {

      throw new EntityException("Ocorreu um erro ao tentar definir agendamento da data e hora informados");

    }

  }

}