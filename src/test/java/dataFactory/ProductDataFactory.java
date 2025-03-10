package dataFactory;
import pojo.ComponentPojo;
import pojo.ProductPojo;
import java.util.ArrayList;
import java.util.List;

public class ProductDataFactory {
    public static ProductPojo createCommomProductWithValueEqualTo(double value) {
        ProductPojo product = new ProductPojo();
        product.setProdutoNome("Macbook M1");
        product.setProdutoValor(value);

        List<String> color = new ArrayList<>();
        color.add("black");
        color.add("sideral");
        product.setProdutoCores(color);

        product.setProdutoUrlMock("");

        List<ComponentPojo> components = new ArrayList<>();
        ComponentPojo component = new ComponentPojo();
        component.setComponenteNome("cable");
        component.setComponenteQuantidade(1);
        components.add(component);

        product.setComponentes(components);

        return product;
    }


}
