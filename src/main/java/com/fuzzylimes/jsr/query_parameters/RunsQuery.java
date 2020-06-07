package com.fuzzylimes.jsr.query_parameters;

import com.fuzzylimes.jsr.resources.RunStatusTypes;
import lombok.Data;

@Data
public class RunsQuery {

    /** user ID; when given, only returns runs played by that user */
    private String user;

    /** when given, only returns runs done by that guest */
    private String guest;

    /** user ID; when given, only returns runs examined by that user */
    private String examiner;

    /** game ID; when given, restricts to that game */
    private String game;

    /** level ID; when given, restricts to that level */
    private String level;

    /** category ID; when given, restricts to that category */
    private String category;

    /** platform ID; when given, restricts to that platform */
    private String platform;

    /** region ID; when given, restricts to that region */
    private String region;

    /** when 1, yes or true, only games run on emulator will be returned */
    private boolean emulated;

    /** filters by run status; new, verified and rejected are possible values for this parameter */
    private RunStatusTypes statusTypes;
}
