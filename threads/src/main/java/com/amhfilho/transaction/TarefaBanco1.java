package com.amhfilho.transaction;

public class TarefaBanco1 implements Runnable {
    PoolDeConexao pool;
    GerenciadorDeTransacao tx;

    public TarefaBanco1(PoolDeConexao pool, GerenciadorDeTransacao tx) {
        this.pool = pool;
        this.tx = tx;
    }

    @Override
    public void run() {
        synchronized (pool){
            System.out.println("Peguei a chave do pool");
            pool.getConnection();

            synchronized (tx){
                System.out.println("Peguei a chave da tx");
                tx.begin();
            }
        }
    }
}
