package com.sqa.lab9.ticket;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

public class CheckinResult {

    private final boolean allowed;
    private final String message;
    private final int scannedCountForShowing;

    public CheckinResult(boolean allowed, String message, int scannedCountForShowing) {
        this.allowed = allowed;
        this.message = message;
        this.scannedCountForShowing = scannedCountForShowing;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public String getMessage() {
        return message;
    }

    public int getScannedCountForShowing() {
        return scannedCountForShowing;
    }
}
