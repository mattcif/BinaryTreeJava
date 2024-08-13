public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public No inserir(int valor) {
        No novoNo = new No(valor);
        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            No atual = this.raiz;
            No pai = null;
            while (atual != null) {
                if (novoNo.getValor() < atual.getValor()) {
                    pai = atual;
                    atual = atual.getEsq();
                } else {
                    pai = atual;
                    atual = atual.getDir();
                }
            }
            if (novoNo.getValor() < pai.getValor()) {
                pai.setEsq(novoNo);
            } else {
                pai.setDir(novoNo);
            }
        }
        return novoNo;
    }

    public boolean remover(int valor) {
        // buscar elemento
        No atual = this.raiz;
        No paiAtual = null;

        while(atual != null) {
            if (atual.getValor() == valor) {
                break;
            } else if (valor < atual.getValor()) {
                paiAtual = atual;
                atual = atual.getEsq();
            } else {
                paiAtual = atual;
                atual = atual.getDir();
            }
        }


        if (atual != null) {
            // elemento tem 2 filhos ou somente filho a direita
            if (atual.getDir() != null ) {
                No substituto = atual.getDir();
                No paiSubstituto = atual;

                while(substituto.getEsq() != null) {
                    paiSubstituto = substituto;
                    substituto = substituto.getEsq();
                }

                if (paiAtual != null) {
                    if (valor < paiAtual.getValor()) {
                        paiAtual.setEsq(substituto);
                    } else {
                        paiAtual.setDir(substituto);
                    }
                } else { // paiAtual == raiz
                    this.raiz = substituto;
                }

                if(substituto != atual.getDir()) {
                    paiSubstituto.setEsq(substituto.getDir());
                    substituto.setDir(atual.getDir());
                }
                substituto.setEsq(atual.getEsq());

            } else if (atual.getEsq() != null) { // filho só a esquerda
                No substituto = atual.getEsq();
                No paiSubstituto = atual;

                while(substituto.getDir() != null) {
                    paiSubstituto = substituto;
                    substituto = substituto.getDir();
                }
                //
                if (paiAtual != null) {
                    if (valor < paiAtual.getValor()) {
                        paiAtual.setEsq(substituto);
                    } else {
                        paiAtual.setDir(substituto);
                    }
                } else {
                    this.raiz = substituto;
                }


                if (substituto != atual.getEsq()) {
                    paiSubstituto.setDir(substituto.getEsq());
                    substituto.setEsq(atual.getEsq());
                }
                substituto.setDir(atual.getDir());

            } else { // não tem filhos
                if (paiAtual != null) {
                    if (valor < paiAtual.getValor()) {
                        paiAtual.setEsq(null);
                    } else {
                        paiAtual.setDir(null);
                    }
                } else {
                    this.raiz = null;
                }
            }

            return true;

        } else {
            return false;
        }
    }

    public void preOrdem(No no) {
        if(no == null) {
            return;
        }
        System.out.println(no.getValor());
        preOrdem(no.getEsq());
        preOrdem(no.getDir());
    }

    public void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.getEsq());
        System.out.println(no.getValor());
        emOrdem(no.getDir());
    }

    public void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.getEsq());
        posOrdem(no.getDir());
        System.out.println(no.getValor());
    }


    public No getRaiz() {
        return this.raiz;
    }
}
