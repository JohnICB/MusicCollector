document.addEventListener( "DOMContentLoaded", getJson);

function checkInput(json)
{
    let output = document.getElementById("feedback");

    let regex = new RegExp('^[a-z0-9]{4,20}$', 'i');
    let text = "";

    if (!regex.test(json.name))  //check lungimea si validitatea la nume / parola etc
    {
        text = "Name must be between 4 an 20 characters long!";
    }
    
    if (json.psw !== json.psw-repeat)
    {
        
    }
}

function getJson()
{
    var form = document.getElementById( "formID" );
    // var output = document.getElementById( "output" );
    form.addEventListener( "submit", function( e ) {
        e.preventDefault();
        var json = toJSONString( this );
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


