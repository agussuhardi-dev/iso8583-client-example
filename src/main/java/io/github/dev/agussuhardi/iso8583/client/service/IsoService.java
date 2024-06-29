package io.github.dev.agussuhardi.iso8583.client.service;

import io.github.dev.agussuhardi.iso8583.client.config.Channel;
import io.github.dev.agussuhardi.iso8583.client.config.Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author agussuhardi
 * {@code @created} 5/14/24 :3:23 PM
 * {@code @project} svc-bni
 */
//@Service
@RequiredArgsConstructor
@Slf4j
@Service
public class IsoService {

    private final Properties properties;

    protected ISOMsg send(ISOMsg msgRequest) {

        try {
            var channel = new Channel(properties);
            channel.connect();
            channel.send(msgRequest);

            ISOMsg response = channel.receive();
            channel.disconnect();
            return response;
        } catch (ISOException | IOException e) {
            log.error("bad send iso message =>{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
