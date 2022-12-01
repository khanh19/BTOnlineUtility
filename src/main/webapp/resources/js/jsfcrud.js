/*
 * Created by Rob Guldi on 2022.11.30
 * Copyright © 2022 Robert Guldi. All rights reserved.
 */

function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}
