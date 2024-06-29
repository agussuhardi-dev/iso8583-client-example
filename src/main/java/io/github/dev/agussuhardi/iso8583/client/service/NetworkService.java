package io.github.dev.agussuhardi.iso8583.client.service;

import io.github.dev.agussuhardi.iso8583.client.dto.NetworkDTO;
import lombok.RequiredArgsConstructor;
import org.jpos.iso.ISODate;
import org.jpos.iso.ISOMsg;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author agussuhardi
 * {@code @created} 6/28/24 10:59 PM
 * {@code @project} iso8583-client-example
 */
@Service
@RequiredArgsConstructor
public class NetworkService {
    private final IsoService isoService;


    public NetworkDTO echo() {
        ISOMsg msg = new ISOMsg("0800");
        msg.set(7, ISODate.getDate(new Date()));
        msg.set(11, "000007"); //System Trace Audit Number
        msg.set(70, "001"); //Network Management Information Code
        var response = isoService.send(msg);
        return new NetworkDTO(response.getString("39"));
    }
}
