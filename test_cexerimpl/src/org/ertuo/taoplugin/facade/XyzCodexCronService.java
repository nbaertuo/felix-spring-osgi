package org.ertuo.taoplugin.facade;

import org.springframework.stereotype.Component;

@Component
public class XyzCodexCronService extends CodexCronCore {

    @Override
    String process(String num) {
        return xyzCodexService.process(num);
    }

}
