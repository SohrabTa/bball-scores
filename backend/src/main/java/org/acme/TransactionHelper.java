package org.acme;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransactionHelper {

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public <T> T withNewTransaction(Supplier<T> supplier) {
        return supplier.get();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public <T> T withNewTransaction(T arg, UnaryOperator<T> function) {
        return function.apply(arg);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void withNewTransaction(Runnable runnable) {
        runnable.run();
    }
}
