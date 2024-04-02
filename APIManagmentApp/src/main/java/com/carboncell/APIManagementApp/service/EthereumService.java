package com.carboncell.APIManagementApp.service;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class EthereumService {

    private final Web3j web3j;
    public EthereumService() {
        this.web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/7536ae2affa5426499311d5bba6706b1"));
    }

    public BigDecimal getAccountBalance(String address) throws Exception {
        // Retrieve balance in Wei and convert to Ether
        BigInteger balanceWei = web3j.ethGetBalance(address, org.web3j.protocol.core.DefaultBlockParameterName.LATEST).send().getBalance();
        return Convert.fromWei(new BigDecimal(balanceWei), Convert.Unit.ETHER);
    }
}
