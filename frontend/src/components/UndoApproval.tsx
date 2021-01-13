import {Button, Loader} from 'semantic-ui-react';
import React, {useState} from 'react';
import {UndoApprovalResult} from '../types';
import Cookies from 'js-cookie';

type Props = {
    auditLogId: string,
    onUndoApproval: (result: UndoApprovalResult) => void
    enabled?: boolean
}

const UndoApproval = ({auditLogId, onUndoApproval, enabled = true}: Props) => {
    const [undoLoading, setUndoLoading] = useState(false)

    function onUndo() {
        setUndoLoading(true)
        const request = {
            auditLogId: auditLogId
        }

        fetch(`/api/v1/auditlog`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get("XSRF-TOKEN") ?? 'NULL'
            },
            body: JSON.stringify(request)
        })
            .then(result => result.json())
            .then(result => {
                onUndoApproval(result)
                setUndoLoading(false)
            })
    }

    if (undoLoading) {
        return (
            <Loader active inline />
        )
    }

    return (
        <Button onClick={() => onUndo()} disabled={!enabled}>
            Undo
        </Button>
    )
}

export default UndoApproval