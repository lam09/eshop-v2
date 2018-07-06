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
    $(document).css("cursor", "progress");
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: "/shop/buyProduct?id=" + productId,
        timeout: 6000,
        success: function (fragment) {
            $("#cart-item-list").replaceWith(fragment); // update snippet of page
            showAlert("Product "+productName+" is added to cart");
            $(document).css("cursor", "default");
            },
        error: function () {
            showAlert("Error when add "+productName+" to cart");
            $(document).css("cursor", "default");
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
        $(".alert").fadeTo(2000, 500).slideDown(500, function () {
          //  $(this).hide();
            $(".alert").stop(true, true).fadeOut({ duration: 1000, queue: false }).slideUp(1000);
          //  $(this).remove();
        });
// 500 : Time will remain on the screen
    }, 1);

  //  $("#alert-addtocart").text(msg);
    //$(".alert").text(msg);
  /*  var slideDuration=1000;
    $("#alert-addtocart").stop(true, true).fadeIn({ duration: slideDuration, queue: false }).css('display', 'none').slideDown(slideDuration,function (){
        $("#alert-addtocart").stop(true, true).fadeOut({ duration: slideDuration, queue: false }).slideUp(slideDuration);
    });*/
}

$(document).ready(function (e) {
    $("#payment-shipping").hide();
})
function goToShipping() {
    var slideDuration=1000;
    $('#payment-shipping').stop(true, true).fadeIn({ duration: slideDuration, queue: false }).css('display', 'none').slideDown(slideDuration);
    //}, function() {
    //$('#myDiv').stop(true, true).fadeOut({ duration: slideDuration, queue: false }).slideUp(slideDuration);
}
