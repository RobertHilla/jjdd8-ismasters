<section class="hero-section set-bg" data-setbg="../../img/koncert.jpg"
         style="background-image: url('../../img/koncert.jpg');" xmlns="http://www.w3.org/1999/html">
    <div class="container">
        <div class="hero-warp">
            <form class="main-search-form" action="/search-events">
                <div class="search-type">
                    <div class="st-item">
<#--                        <input type="radio" name="st" id="buy" checked="">-->
                        <label for="buy">Wyszukaj wydarzenie</label>
                    </div>
                </div>
                <div class="search-input">
                    <input type="text" title="Wyszukiwanie, wpisz co najmniej 3 znaki"  name="eventsearch" class="form-control form-control-user" pattern=".{3,}"
                           id="eventsearch" placeholder="Wyszukiwanie, wpisz co najmniej 3 znaki." autocomplete="off" ></div>
                <div id="result-div" class="list-group" style="margin-top:2px;position:absolute;margin-left:0;z-index: 1001 !important; ">
                </div>

                <input id="datepicker" width="276" name = "startDate" title="Data rozpoczęcia" />
                </br>
                <input id="datepicker2" width="276" name = "endDate" title="Data zakończenia"/>
                <script>
                    $('#datepicker').datepicker({
                        uiLibrary: 'bootstrap4'
                    });

                    $('#datepicker2').datepicker({
                        uiLibrary: 'bootstrap4'
                    });
                </script>





<#--                <button class="site-btn">Wyszukaj</button>-->
            </form>
        </div>
    </div>
</section>