/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.ejec;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Iterator;
    import java.util.List;

public class Combinar {

    List<String> ListaUno;
    List<String> ListaDos = new ArrayList<String>();

    public Combinar(List<String> list) {
        ListaUno = new ArrayList<String>();
        sinPermutacion(list);
        ListaDos = list;
    }

    public String Arreglo() {
        String cad = "";
        Iterator iter = ListaUno.iterator();

        int tamanio = ListaDos.size();//d=lista que nos envien
        int x = 1;
        double a[] = new double[tamanio];

        for (int m = 1; m <= tamanio; m++) {
            double n = 1;
            double r = 1;
            double auxUno = 1;
            for (int i = 1; i <= tamanio; n *= i, i++);//factorial de n que será el número de elementos.
            int aux = (tamanio - m);
            for (int i = 1; i <= aux; auxUno *= i, i++);//factorial de auxUno, que es la resta anterior
            for (int i = 1; i <= m; r *= i, i++);//factorial del número de elementos por grupo
            a[m - 1] = n / (auxUno * r);//formula para obtener número de combinaciones posibles y lo guardamos en un arreglo.
        }

        while (iter.hasNext()) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 1; j <= a[i]; j++) {
                    for (int k = 1; k <= x; k++) {
                        cad += (String) iter.next() + "_";//colocamos un separador
                    }//comb.add("\n");
                    cad += "\n";//colacamos un salto de linea
                }
                x++;
            }
        }
        return cad;
    }

    public void sinPermutacion(List lista) {
        Object[] o = lista.toArray();
        for (int m = 1; m <= lista.size(); m++) {
            int[] posArr = new int[m];
            posArr[0] = 0;
            if (m > 1) {
                for (int i = 1; i < m; i++) {
                    posArr[i] = i;
                }
            }
            combina(posArr, m - 1, m, o);
        }
    }

    public void combina(int[] posArr, int posCam, int dea, Object[] o) {
        int cantidad = o.length;
        int j;

        for (j = 0; j < posArr.length; j++) {
            ListaUno.add((String) o[posArr[j]]);
        }

        posArr[posCam]++;

        if (posArr[posCam] < cantidad) {
            combina(posArr, posCam, dea, o);
        } else {
            int nuevaPosCam = posCam - 1;

            if (nuevaPosCam >= 0) {
                posArr[nuevaPosCam]++;
                posArr[posCam] = posArr[posCam - 1] + 1;

                if (posArr[nuevaPosCam] < (cantidad - 1)) {
                    combina(posArr, posCam, dea, o);
                } else {
                    boolean salida = false;
                    if (nuevaPosCam != 0) {
                        while (posArr[nuevaPosCam] >= cantidad - 1 || (salida && nuevaPosCam > 0)) {
                            nuevaPosCam--;
                            posArr[nuevaPosCam]++;
                            for (int i = nuevaPosCam + 1; i < dea; i++) {
                                posArr[i] = posArr[i - 1] + 1;
                                salida = posArr[i] == cantidad;
                            }
                        }
                        if (!salida) {
                            combina(posArr, posCam, dea, o);
                        }
                    }
                }
            }//end if nuevaPosCam>=0
        }//end else
    }//end combina

    public static void main(String ar[]) {
        List<String> lista = new ArrayList<String>();
        lista.add("1");
        lista.add("2");
        lista.add("3");
        lista.add("4");
        Combinar comb = new Combinar(lista);
        System.out.println(comb.Arreglo());
    }
}
