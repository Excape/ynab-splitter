import React from "react";
import {Button} from "semantic-ui-react";
import {ApprovalFor} from '../../types';
import {getColor} from '../utils';

type Props = {
    actors: string[]
    onSelect: (approvalOption: ApprovalFor) => void
}

const ApprovalButtons = ({actors, onSelect}: Props) => {
    const [approveOpenFor, setApproveOpenFor] =
        React.useState<ApprovalFor>({actor: undefined, splitApproval: false})


    function handleApprove(approvalOption: ApprovalFor) {
        setApproveOpenFor(approvalOption)
        onSelect(approvalOption)
    }

    function isOpen(approvalOption: ApprovalFor) {
        // compare fields because the objects get recreated and are not equal
        return approveOpenFor.actor === approvalOption.actor &&
            approveOpenFor.splitApproval === approvalOption.splitApproval;
    }

    const splitApprovalOption = {actor: undefined, splitApproval: true}

    return (
        <div className='buttonRow'>
            {actors.map((actor, i) => {
                    const approvalOption: ApprovalFor = {actor: actor, splitApproval: false}
                    return (
                        <Button fluid
                                key={i}
                                basic={!isOpen(approvalOption)}
                                color={getColor(i)}
                                content={actor}
                                onClick={() => handleApprove(approvalOption)}/>
                    )
                }
            )}

            <Button fluid
                    basic={!isOpen(splitApprovalOption)}
                    color="grey"
                    content={"Split"}
                    onClick={() => handleApprove(splitApprovalOption)}
            />
        </div>)
}

export default ApprovalButtons