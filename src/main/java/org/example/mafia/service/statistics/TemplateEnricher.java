package org.example.mafia.service.statistics;

import java.util.Map;

public interface TemplateEnricher {
    Map<String, Object> enrich(Map<String, Object> map, String gamerId);
}
