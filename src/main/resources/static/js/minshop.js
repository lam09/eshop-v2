/**
 * Created by a.lam.tuan on 26. 6. 2018.
 */

function addToCart(productId) {
    console.log("add " + productId + " to cart");
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: "/shop/buyProduct?id=" + productId,
        timeout: 6000,
        success: function (fragment) {
            $("#cart-item-list").replaceWith(fragment); // update snippet of page
            }
    });

}