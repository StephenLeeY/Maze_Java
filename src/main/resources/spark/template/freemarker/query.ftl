<#assign content>

    <h1> STARS </h1>

    <div class="queries">
        <div class="neighbors-query">
            <p> Search using neighbors command </p>

            <div class = "instructions">
                <div class = "commands">
                    <ul>
                        <li> <p> &lt;int&gt;  &lt;double&gt; &lt;double&gt;  &lt;double&gt; </p> </li>
                        <li> <p> &lt;int&gt;   &lt;"Name"&gt;</p> </li>
                    </ul>
                </div>
            </div>

            <form method="POST" action="/results">
                <input type="text" name="neighborsParams" id="neighborsParams"> <br>
                <span style="display:flex; justify-content:flex-end; width:100%; padding:0;">
                        <input type="submit" value="submit"/>
                </span>
            </form>
            ${neighborResults}
        </div>

        <div class="radius-query">
            <p> Search using radius command </p>

            <div class = "instructions">
                <div class = "commands">
                    <ul>
                        <li> <p> &lt;double&gt;  &lt;double&gt; &lt;double&gt;  &lt;double&gt; </p> </li>
                        <li> <p> &lt;double&gt;   &lt;"Name"&gt;</p> </li>
                    </ul>
                </div>
            </div>
            <form method="POST" action="/results">
                <input type="text" name="radiusParams" id="radiusParams"> <br>
                    <span style="display:flex; justify-content:flex-end; width:100%; padding:0;">
                        <input type="submit" value="submit"/>
                    </span>
            </form>
            ${radiusResults}
        </div>
    </div>

    <div class = "error-mssg">
        ${errorMessage}
    </div>

</#assign>
<#include "main.ftl">