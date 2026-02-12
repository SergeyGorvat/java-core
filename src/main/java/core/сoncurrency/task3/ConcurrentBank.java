package core.сoncurrency.task3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Управление счетами и переводами
 */

public class ConcurrentBank {
    private final Map<String, BankAccount> accounts = new ConcurrentHashMap<>();
    private final AtomicLong accountIdCounter = new AtomicLong(0);

    /**
     * Создание нового счета
     *
     * @param initialBalance
     * @return
     */
    public BankAccount createAccount(long initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс должен быть > 0");
        }

        String accountId = "AAA" + accountIdCounter.incrementAndGet();

        BankAccount account = new BankAccount(accountId, initialBalance);

        accounts.put(accountId, account);

        System.out.println("Новый счет: " + accountId + " создан с балансом " + initialBalance);
        return account;
    }

    /**
     * Перевод между счетами
     *
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @return
     */
    public boolean transfer(BankAccount fromAccount, BankAccount toAccount, long amount) {
        if (amount < 10) {
            System.out.println("Ошибка: сумма перевода должна быть >= 10");
            return false;
        }

        if (fromAccount == toAccount) {
            System.out.println("Ошибка: нельзя переводить на один и тот же счет");
            return false;
        }

        Object lock1, lock2;
        if (fromAccount.getAccountId().compareTo(toAccount.getAccountId()) < 0) {
            lock1 = fromAccount.getLock();
            lock2 = toAccount.getLock();
        } else {
            lock1 = toAccount.getLock();
            lock2 = fromAccount.getLock();
        }

        synchronized (lock1) {
            synchronized (lock2) {
                if (fromAccount.getBalance() < amount) {
                    System.out.println(" " + fromAccount.getAccountId() +
                            " → " + toAccount.getAccountId() + ": Ошибка - недостаточно средств на счете");
                    return false;
                }

                long fromBalance = fromAccount.getBalance();
                fromAccount.withdraw(amount);

                toAccount.deposit(amount);

                System.out.println(" " + fromAccount.getAccountId() +
                        " → " + toAccount.getAccountId() + ": " + amount);
                return true;
            }
        }
    }

    /**
     * Общий баланс счетов
     *
     * @return
     */
    public long getTotalBalance() {
        long total = 0;

        for (BankAccount value : accounts.values()) {
            total += value.getBalance();
        }

        return total;
    }
}
