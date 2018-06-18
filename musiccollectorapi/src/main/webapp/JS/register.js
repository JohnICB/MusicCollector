document.addEventListener( "DOMContentLoaded", getJson);

function sendToServer(json, output)
{

    console.log("trimit " + json.toString());

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8081/register");

    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                console.log("Success " + xhr.response);
                let jsonResp = JSON.parse(xhr.response);
                printFeedback(output, jsonResp.text, jsonResp.isError);
                break;
            case 404:
                console.log("Oups! Not found");
                break;
        }

    });

    xhr.addEventListener("error", function errorCallback() {
        console.log("Network error");
    });

    xhr.send(JSON.stringify(json));
}

function checkInput(json)
{
    let output = document.getElementById("feedback");
    let valid = true;
    let regex = new RegExp('^[a-z0-9]{4,20}$', 'i');
    let text = "";

    if (!regex.test(json.username))  //check lungimea si validitatea la nume / parola etc
    {
        text = text + "Name must be alphanumeric and between 4 and 20 characters long!\n";
        valid = false;
    }
    if (json.psw !== json.pswrepeat)
    {
        text = text + "Passwords don't match\n";
        valid = false;
    }
    if (!regex.test(json.psw))
    {
        text = text + "Password must be alphanumeric and between 4 and 20 characters long!\n"
        valid = false;
    }

    console.log(text);

    if (!valid)
    {
        printFeedback(output, text, true)
    }
    else if (valid)
    {
        sendToServer(json, output);
    }
    
}

function printFeedback(output, text, isError)
{
    output.innerText = text;
    output.style.borderRadius = "5px";
    output.style.padding = "5px";
    if (isError)
    {
        output.style.backgroundColor = "#ffdfdc";
        output.style.color = "#b30000";

    }
    else
    {
        output.style.backgroundColor = "#d5ffc4";
        output.style.color = "#00b307";

        let div = document.getElementsByClassName("container")[0];
        div.innerHTML = "";
        div.appendChild(output);

        setTimeout(function () {
            window.location.href = "http://localhost:8081/welcome";
        }, 3000);

    }


}

function getJson()
{
    var form = document.getElementById( "formID" );
    // var output = document.getElementById( "output" );
    form.addEventListener( "submit", function( e ) {
        e.preventDefault();
        var json = toJSONString( this );
        json = JSON.parse(json);

        console.log(json);
        checkInput(json);
    }, false);
}
function toJSONString( form ) {
    var obj = {};
    var elements = form.querySelectorAll( "input, select" );
    for( var i = 0; i < elements.length; ++i ) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if( name ) {
            obj[ name ] = value;
        }
    }

    return JSON.stringify( obj );
}


