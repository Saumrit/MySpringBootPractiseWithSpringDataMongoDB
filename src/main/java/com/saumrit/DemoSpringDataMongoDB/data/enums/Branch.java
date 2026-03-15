package com.saumrit.DemoSpringDataMongoDB.data.enums;

public enum Branch {
    CSE("ComputerScience Engineering"),
    CIVIL("Civil Engineering"),
    MECH("Mechanical Engineering"),
    ELECTTRICAL("Electrical Engineering"),
    PRODUCTION("Production Engineering");

    Branch(String branchName) {
        this.branchName=branchName;
    }

    final String branchName;

    public String getBranchName(){
        return this.name();
    }
}
