package core.сoncurrency.task3;

/**
 * Банковский счет
 */

public class BankAccount {
    private final String accountId;
    private volatile long balance;
    private final Object lock = new Object();

    /**
     * @param accountId
     * @param initialBalance
     */
    public BankAccount(String accountId, long initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    /**
     * Пополнение счета
     *
     * @param amount
     * @throws IllegalArgumentException
     */
    public void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Минимальная сумма пополнения должна быть > 0");
        }

        synchronized (lock) {
            balance += amount;
        }
    }

    /**
     * Снятие со счета
     *
     * @param amount
     * @return
     * @throws IllegalArgumentException
     */
    public boolean withdraw(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма снятия со счета должна быть > 0");
        }

        synchronized (lock) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Вывести " + accountId + ": -" + amount + " Остаток на балансе: " + balance);
                return true;
            } else {
                System.out.println("Вывести " + accountId + ": ОШИБКА - недостаточно средств на счете");
                return false;
            }
        }
    }

    /**
     * Получить текущий баланс счета
     *
     * @return
     */
    public long getBalance() {
        return balance;
    }

    /**
     * Получить идентификатор счета
     *
     * @return
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Получить блокировку для синхронизации между счетами
     *
     * @return
     */
    public Object getLock() {
        return lock;
    }
}
