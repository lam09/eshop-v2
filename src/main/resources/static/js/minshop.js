/**
 * Created by a.lam.tuan on 26. 6. 2018.
 */

function login() {
    var username=$('#name').val();
    var password=$('#password').val();;
    $.ajax({
       // type: 'POST',
        contentType: "application/json",
        url: "/j_spring_security_check?name="+username+"&password="+password,
        timeout: 6000,
        success: function () {

        }
    });
}

function addToCart( productId,productName) {
    $("body").css("cursor", "progress");
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: "/shop/buyProduct?id=" + productId,
        timeout: 6000,
        success: function (fragment) {
            $("#cart-item-list").replaceWith(fragment); // update snippet of page
            showAlert("Product "+productName+" is added to cart");
            $("body").css("cursor", "default");
            }
    });

}
function updateCartProductQuantity(productId) {
    var productId= $('#product-id-'+productId).val();
    var quantity=$('#product-quantity-'+productId).val();
    console.log("update quantity of product" + productId + " to " + quantity);
    if(productId<1) {
        alert("Quantity can not under 1");
        return;
    }
    $.ajax({
       // type: 'POST',
        contentType: "application/json",
        url: "/shop/updateCartProductQuantity?id=" + productId+"&quantity="+quantity,
        timeout: 6000,
        success: function (fragment) {
            $("#cart").replaceWith(fragment); // update snippet of page

            $.get("/shop/smallCart", function (fragment) { // get from controller
                $("#cart-item-list").replaceWith(fragment); // update snippet of page
            });
        }
    });

}

function removeCartProduct(productId) {
    var productId= $('#product-id-'+productId).val();

    $.ajax({
      //  type: 'POST',
        contentType: "application/json",
        url: "/shop/removeCartProduct?id=" + productId,
        timeout: 6000,
        success: function (fragment) {
            $("#cart").replaceWith(fragment); // update snippet of page
            $.get("/shop/smallCart", function (fragment) { // get from controller
                $("#cart-item-list").replaceWith(fragment); // update snippet of page
            });
        }
    });

}
function showAlert(msg) {
    window.setTimeout(function () {
        $(".alert").text(msg);
        $(".alert").fadeTo(2000, 500).slideUp(500, function () {
            $(this).hide();
          //  $(this).remove();
        });
// 500 : Time will remain on the screen
    }, 500);
}