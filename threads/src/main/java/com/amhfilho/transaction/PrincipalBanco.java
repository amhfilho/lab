package com.amhfilho.transaction;

public class PrincipalBanco {
    public static void main(String[] args) {

        GerenciadorDeTransacao tx = new GerenciadorDeTransacao();
        PoolDeConexao pool = new PoolDeConexao();

        new Thread(new TarefaBanco1(pool,tx)).start();
        new Thread(new TarefaBanco2(pool,tx)).start();
    }
}
