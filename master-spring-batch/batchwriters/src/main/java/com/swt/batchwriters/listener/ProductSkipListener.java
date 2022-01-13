package com.swt.batchwriters.listener;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.file.FlatFileParseException;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author vsushko
 */
public class ProductSkipListener {

    private final String readErrFileName = "error/read_skipped";
    private final String processErrFileName = "error/processErr_skipped";

    @OnSkipInRead
    public void onSkipRead(Throwable t) {
        if (t instanceof FlatFileParseException) {
            FlatFileParseException ffep = (FlatFileParseException) t;
            onSkip(ffep.getInput(), readErrFileName);
        }
    }

    @OnSkipInProcess
    public void onSkipInProcess(Object item, Throwable t) {
        if (t instanceof RuntimeException) {
            onSkip(item, processErrFileName);
        }
    }

    @OnSkipInWrite
    public void onSkipInWrite(Object item, Throwable t) {
        if (t instanceof RuntimeException) {
            onSkip(item, processErrFileName);
        }
    }

    public void onSkip(Object o, String fname) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(fname, true);
            fos.write(o.toString().getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
