package com.lam.eshopv2.model;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */
import com.lam.eshopv2.entity.PaymentMethod;
import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.entity.ShippingMethod;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {
    private int orderNum;
    private ShippingAddressInfo shippingAddressInfo;

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    private PaymentMethod paymentMethod;
    private ShippingMethod shippingMethod;
    private double amount=0.00f;
    private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();


    public void updateAmount() {
        amount=0.00f;
        cartLines.stream().forEach(line->{
            amount+=line.getQuantity()*line.getProduct().getPrice();
        });
        cartLines.stream().forEach(cartLineInfo -> cartLineInfo.setAmount(cartLineInfo.getQuantity()*cartLineInfo.getProduct().getPrice()));
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public CartInfo() {

    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public ShippingAddressInfo getShippingAddressInfo() {
        return shippingAddressInfo;
    }

    public void setShippingAddressInfo(ShippingAddressInfo shippingAddressInfo) {
        this.shippingAddressInfo = shippingAddressInfo;
    }

    public List<CartLineInfo> getCartLines() {
        return this.cartLines;
    }

    private CartLineInfo findLineByCode(String code) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getProduct().getCode().equals(code)) {
                return line;
            }
        }
        return null;
    }

    public void addProduct(Product product, int quantity) {
        CartLineInfo line = this.findLineByCode(product.getCode());

        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setProduct(product);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
        updateAmount();
    }

    public void validate() {

    }

    public void updateProduct(String code, int quantity) {
        CartLineInfo line = this.findLineByCode(code);

        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
        updateAmount();
    }

    public void removeProduct(Product product) {
        CartLineInfo line = this.findLineByCode(product.getCode());
        if (line != null) {
            this.cartLines.remove(line);
        }
        updateAmount();
    }

    public void updateProductQuantity(Product product,Integer quantity) {
        updateProduct(product.getCode(),quantity);
    }

    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }

    public boolean isValidCustomer() {
        return this.shippingAddressInfo != null && this.shippingAddressInfo.isValid();
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }

    public void updateQuantity(CartInfo cartForm) {
        if (cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLines();
            for (CartLineInfo line : lines) {
                this.updateProduct(line.getProduct().getCode(), line.getQuantity());
            }
        }

    }

}