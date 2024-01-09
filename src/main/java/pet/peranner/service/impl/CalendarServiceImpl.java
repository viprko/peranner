package pet.peranner.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.peranner.exception.IllegalPeriodException;
import pet.peranner.model.DevoteTime;
import pet.peranner.model.User;
import pet.peranner.service.CalendarService;
import pet.peranner.service.DevoteTimeService;
import pet.peranner.strategy.PeriodStrategy;

@Service
public class CalendarServiceImpl implements CalendarService {
    private static final LocalTime END_OF_DAY = LocalTime.of(23, 59, 59);
    private final DevoteTimeService devoteTimeService;
    private final Map<String, PeriodStrategy> periodStrategyMap;

    @Autowired
    public CalendarServiceImpl(List<PeriodStrategy> periodStrategies,
                               DevoteTimeService devoteTimeService) {
        periodStrategyMap = periodStrategies.stream()
                .collect(Collectors.toMap(
                        strategy -> strategy.getClass().getAnnotation(Service.class).value(),
                        Function.identity()));
        this.devoteTimeService = devoteTimeService;
    }

    @Override
    public List<DevoteTime> findDevoteTimeForPeriod(String period, User currentUser) {
        try {
            PeriodStrategy periodStrategy = periodStrategyMap.get(period);
            return periodStrategy.fetchDevoteTimeForPeriod(currentUser);
        } catch (IllegalArgumentException e) {
            throw new IllegalPeriodException(
                    "Invalid period: " + period + ". There are 3 period for pick: "
                            + System.lineSeparator() + "ten-days, month, year", e);
        }
    }

    @Override
    public List<DevoteTime> findDevoteTimeForCustomPeriod(LocalDate periodStart,
                                                          LocalDate periodEnd, User currentUser) {
        return devoteTimeService.findByPeriod(periodStart.atStartOfDay(),
                periodEnd.atTime(END_OF_DAY),
                currentUser.getEmail());
    }
}
