(function () {
    $(function () {
        /*
        Show button tooltip by data attribute
         */
        $('[data-toggle="tooltip"]').tooltip();
        /*
        Saved alerts
         */
        setInterval(function(){
            if ( window.location.hash == "#success") {
                history.pushState('', document.title, window.location.pathname);
                $('#ok-alert').show(100);
                setTimeout(function(){ $('#ok-alert').hide(100); },4000);
            }
            if ( window.location.hash == "#error") {
                history.pushState('', document.title, window.location.pathname);
                $('#warning-alert').show(100);
                setTimeout(function(){ $('#warning-alert').hide(100); },4000);
            }
        },200);
    });
})();