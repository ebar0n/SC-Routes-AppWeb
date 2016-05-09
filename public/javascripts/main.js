$(document).on("ready",ready);
function ready(e)
{
	/**CHOSEN**/
	$(".select-chosen").chosen({no_results_text: "Oops, no hay resultados que coincidad! para: "});
	$(".select-chosen-deselect").chosen({allow_single_deselect:true});
}