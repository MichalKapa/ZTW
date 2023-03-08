<?php
/*
 * Plugin Name:       Ad Manager
 * Description:       Simple Ad Manager for Advanced Web Technologies classes.
 * Version:           0.1
 * Author:            Michał Kapała, Piotr Łazik
 */
function am_admin_actions_register_menu(){
    add_options_page("Ad Manager", "Managing Ads System", 'manage_options', "admana", "am_admin_page");
}
   
add_action('admin_menu', 'am_admin_actions_register_menu'); 

function am_admin_page(){
    global $_POST;
    if(isset($_POST['am_end_form'])){
        if($_POST['am_end_form'] == 'Y'){
            $postEndDate = $_POST['ad_end_time'];
            echo '<div class="notice notice-success isdismissible"><p>End date saved.</p></div>';
            update_option('am_ad_end', $postEndDate);
        }
    }
    if(isset($_POST['am_delete_end_date'])){
        if($_POST['am_delete_end_date'] == 'Y'){
            echo '<div class="notice notice-success isdismissible"><p>End date deleted.</p></div>';
            update_option('am_ad_end', "");
        }
    }


    $postEndDate = get_option('am_ad_end');
?>

<div class="wrap">
    <h1>Ad Manager</h1>
    <h3>Data zakończenia reklamy</h3>
    <?php $koniec = !empty($postEndDate) ? $postEndDate : "Brak końca reklamy";
    echo $koniec; ?>
    <h2></h2>
    <form name="am_end_form" method="post">
        <input type="hidden" name="am_end_form" value="Y">
        <input type="datetime-local" id="end-time"
            name="ad_end_time">
        <p class="submit"><input type="submit" value="Ustaw koniec"></p>
    </form>
    <form name="am_delete_end_date" method="post">
        <input type="hidden" name="am_delete_end_date" value="Y">
        <p class="submit"><input type="submit" value="Usuń datę"></p>
    </form>
</div>
<?php
}



function am_add_ad($content) {

    if (str_contains($content, "KONIEC REKLAMY")) {
        return $content;
    }

    $posts = get_posts();
    shuffle($posts);
    $am_post_id = -1;
    $adFound = false;

    while(count($posts) != 0 && !$adFound) {
        $possibleAd = array_pop($posts);
        if (str_contains(get_the_title($possibleAd), "REKLAMA")) {
            $am_post_id = $possibleAd->ID;
            $adFound = true;
        }
    }

    $adPost = get_post($am_post_id)->post_content;
    $amAdEnd = get_option('am_ad_end');


    if(!$adFound) {
        return $content;
    }
    if($amAdEnd < date("Y-m-d H:i") && !empty($amAdEnd)){
        return $content;
    }


    $jsScript = '<script>
            var dbEndDate = "'.$amAdEnd.'"
            console.log(dbEndDate);
            var endDate = Date.parse(dbEndDate);
            countDown("countdown", endDate);
            
            function countDown(element, date){
                if(endDate){
                    var x = setInterval(function() {
                        var now = new Date().getTime();
                        var timeDiff = date - now;
                        
                        var days = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
                        var hours = Math.floor((timeDiff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                        var minutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
                        var seconds = Math.floor((timeDiff % (1000 * 60)) / 1000);
                        
                        document.getElementById(element).innerHTML = "Do końca pozostało: " + days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
                        
                        if (timeDiff < 0) {
                            clearInterval(x);
                            document.getElementById(element).innerHTML = "Reklama zakoczyła się";
                        }
                    }, 1000)
                }
            };
        </script>';
    return $jsScript.'<div style="border: 2px solid black"><p style="color: red">REKLAMA</p><div id="countdown"></div>'.str_replace("KONIEC REKLAMY","",$adPost).'</div>'.$content;
}
add_filter("the_content", "am_add_ad"); 
?>